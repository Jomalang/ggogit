class LeafNode {

    constructor(leaf) {
        this.data = leaf;
        this.parent = null;
        this.children = [];
    }

    hasChildren() {
        return this.children.length > 0;
    }

    getChildNodes() {
        return this.children;
    }

    getParentNode() {
        return this.parent;
    }
}

class LeafTree {

    constructor() {
        this.root = null;
        this.leafNodes = [];
        this.branchNodes = [];
    }

    async init() {
        await this.apiTree();
        const leafId = this.getURLLeafId();
        this.renderAllLeafNode(leafId);
    }

    async apiTree() {
        const tree_id = this.getURLTreeId();
        const leaf_id = this.getURLLeafId();

        try {
            const response = await fetch(`/api/v1/trees/${tree_id}/leafs/${leaf_id}`);
            if (!response.ok) {
                throw new Error('서버에서 데이터를 불러오는데 실패했습니다.');
            }
            const data = await response.json();
            // console.log(data);
            data.forEach(leaf => {
                // console.log(leaf);
                this.addNode(leaf);
            });
        } catch (error) {
            console.error('데이터를 불러오는데 실패했습니다.', error);
        }
    }

    renderAllLeafNode(leafId) {
        const branchNodes = this.getBranchAllNodesByLeafId(leafId);
        this.render(branchNodes);
    }

    renderToEndLeafNode(leafId) {
        const branchNodes = this.getBranchToEndNodesByLeafId(leafId);
        this.render(branchNodes);
    }

    render(branchNodes) {
        let lastNode = null;
        branchNodes.forEach((node, index) => {
            let sectionTag = this.templateLeafNodeRow(node);
            this.addSideScrollEvent(sectionTag);
            const listContainer = document.querySelector('section.log-list-container');
            listContainer.insertAdjacentElement('beforeend', sectionTag);

            setTimeout(() => {
                sectionTag.classList.add('log-item-container--visible');
            }, 100 * index); // 0.2초 간격으로 나타나기

            lastNode = node;
        });

        // 브랜치 정보 변경
        this.changeBranchInfo(lastNode.data.id);

    }

    async changeBranchInfo(leafId) {
        const treeId = this.getURLTreeId();
        try {
            const response = await fetch(`/api/v1/trees/${treeId}/leafs/${leafId}/branch`)
            if (!response.ok) {
                throw new Error('서버에서 데이터를 불러오는데 실패했습니다.');
            }
            const data = await response.json();
            // console.log(data);

            let branchTag = document.querySelector('div.branch-state-main');
            branchTag.querySelector('p.branch-state__name').innerText = data.branchName;
            let stateNumTags = branchTag.querySelectorAll('div.branch-state__stats > p > span');
            // console.log(stateNumTags);
            stateNumTags[0].innerText = data.leafCount;
            stateNumTags[1].innerText = data.likeCount;
            stateNumTags[2].innerText = data.viewCount;
            branchTag.querySelector('p.branch-state__date').innerText =
                window.formatLocalDateTimeToDate(data.updateTime);

        } catch (error) {
            console.error('데이터를 불러오는데 실패했습니다.', error);
        }
    }

    addSideScrollEvent(node) {
        if (node.children.length === 1) {
            return; // 자식이 1개인 경우 스크롤 이벤트 추가하지 않음
        }

        // console.log('scroll event', node);
        const cooldown = 1000; // 쿨다운 시간 (밀리초단위 1000 = 1초)
        let lastEventTime = 0; // 마지막 이벤트 발생 시간

        let startX;
        let currentIndex = node.dataset.index;
        const threshold = window.innerWidth * 0.2; // 스와이프 이동을 위한 임계값


        node.addEventListener('touchstart', (event) => {
            startX = event.touches[0].pageX - node.offsetLeft;
        });

        node.addEventListener('touchmove', async (event) => {
            const currentTime = new Date().getTime();

            if (currentTime - lastEventTime < cooldown) {
                return; // 쿨다운 시간 동안 이벤트 발생하지 않음
            }

            const x = event.touches[0].pageX - node.offsetLeft;
            const distance = x - startX;

            if (Math.abs(distance) > threshold) {
                let isMove = false;
                if (0 < distance && 0 < currentIndex) {
                    currentIndex--; // 왼쪽으로 스와이프
                    isMove = true;
                } else if (distance < 0 && currentIndex < node.children.length - 1) {
                    currentIndex++; // 오른쪽으로 스와이프
                    isMove = true;
                }

                if (!isMove) {
                    return; // 스와이프 이동하지 않음
                }

                node.style.transform = `translateX(-${currentIndex * window.innerWidth}px)`;
                lastEventTime = currentTime; // 이벤트 발생 시간 업데이트
                node.dataset.index = currentIndex;

                setTimeout(() => {
                    window.findFocusNode(); // 활성화 노드 찾기
                }, 500); // 1초 뒤 초기화

                // 하단 노드 삭제
                if (node.nextElementSibling !== null) {
                    this.removeLeafNode(node.nextElementSibling);
                }

                // 새로운 노드 추가
                // console.log('currentIndex', currentIndex);
                const leafId = node.querySelector('div.log-item__circle').dataset.id;
                const leafNode = this.findNode(leafId);
                const childLeafId = leafNode.data.childLeafIds[currentIndex];

                // 이미 호출했는지 확인
                if (!this.hasNode(childLeafId)) {
                    await this.apiLeafToEnd(childLeafId);
                }

                // 하위 랜더링
                this.renderToEndLeafNode(childLeafId);
            }
        });
    }

    hasNode(leafId) {
        for (let node of this.leafNodes) {
            if (node.data.id === leafId) {
                return true;
            }
        }
        return false;
    }

    async apiLeafToEnd(leafId) {
        const treeId = this.getURLTreeId();
        try {
            const response = await fetch(`/api/v1/trees/${treeId}/leafs/${leafId}/children`);
            if (!response.ok) {
                throw new Error('서버에서 데이터를 불러오는데 실패했습니다.');
            }
            const data = await response.json();
            // console.log(data);
            data.forEach(leaf => {
                this.addNode(leaf);
            });
        } catch (error) {
            console.error('데이터를 불러오는데 실패했습니다.', error);
        }
    }

    removeLeafNode(node) { // 노드 삭제
        if (node.nextElementSibling === null) {
            node.remove();
            return; // 재귀로 삭제
        }
        this.removeLeafNode(node.nextElementSibling);
        node.remove();
    }

    addNode(node) {
        const leafNode = new LeafNode(node);
        if (this.root === null && node.parentLeafId === null) {
            this.root = leafNode;
            this.leafNodes.push(leafNode);
            this.branchNodes.push(leafNode);
            return; // 루트 노드인 경우
        }

        // 루트 노드 아닌 경우
        const parentNode = this.findParentNode(node.parentLeafId);
        if (parentNode === undefined) {
            throw new Error('부모 노드를 찾을 수 없습니다.');
        }

        parentNode.children.push(leafNode);
        leafNode.parent = parentNode;
        this.leafNodes.push(leafNode);

        if (0 <= leafNode.children.length && leafNode.children.length < 2) {
            this.branchNodes.push(leafNode); // 자식 0이상 3미만인 경우
        }
    }

    getURLLeafId() {
        const url = window.location.search;
        const params = new URLSearchParams(url);
        return parseInt(params.get('leaf_id'));
    }

    getURLTreeId() {
        const url = window.location.search;
        const params = new URLSearchParams(url);
        return parseInt(params.get('tree_id'));
    }

    findParentNode(parentLeafId) {
        return this.branchNodes.find(node => node.data.id === parentLeafId);
    }

    findNode(leafId) {
        return this.leafNodes.find(node => node.data.id === parseInt(leafId));
    }

    getBranchAllNodesByLeafId(leafId) {
        const branchNodes = [];
        this.findToEnd(leafId, branchNodes);
        this.findToRoot(leafId, branchNodes);
        branchNodes.sort((a, b) => a.data.id - b.data.id); // id 값이 적은 순서대로 정렬
        return branchNodes;
    }

    getBranchToEndNodesByLeafId(leafId) {
        const branchNodes = [];
        branchNodes.push(this.findNode(leafId));
        this.findToEnd(leafId, branchNodes);
        return branchNodes;
    }

    findToEnd(leafId, branchNodes) {
        let startToEndNode = this.findNode(leafId);

        while (startToEndNode.hasChildren()) {
            let childNodes = startToEndNode.getChildNodes();
            if (childNodes.length === 1) {
                startToEndNode = childNodes[0];
            }
            else if (childNodes.length === 2) {
                startToEndNode = childNodes[0];
            }
            else if (childNodes.length === 3) {
                startToEndNode = childNodes[1];
            }
            branchNodes.push(startToEndNode);
        }
    }

    findToRoot(leafId, branchNodes) {
        let startToRootNode = this.findNode(leafId);

        while (startToRootNode != null) {
            branchNodes.push(startToRootNode);
            startToRootNode = startToRootNode.getParentNode();
        }
    }

    templateLeafNodeRow(leafNode) {

        const section = document.createElement('section');
        section.classList.add('log-item-container');
        section.classList.add('log-item-container--hidden');

        const childLength = leafNode.data.childLeafIds.length;
        if (childLength === 0 || childLength === 1 || childLength === 2) {
            section.dataset.index = 0;
            section.style.transform = `translateX(0)`;
        }
        else if (childLength === 3) {
            section.dataset.index = 1;
            section.style.transform = `translateX(-${window.innerWidth}px)`;
        }

        section.innerHTML = `
            ${this.templateLeafNodeLeft(leafNode)}
            ${this.templateLeafNodeMiddle(leafNode)}
            ${this.templateLeafNodeRight(leafNode)}
        `;

        return section;
    }

    templateLeafNodeLeft(leafNode) {

        if (leafNode.data.childLeafIds.length !== 3) {
            return ``; // 자식이 2개가 아닌 경우 그릴 필요 없음
        }

        let template = '';

        if (leafNode.data.direction === 3) {
            template = this.templateLeafNode(2, leafNode);
        }

        if (leafNode.data.direction === 7) {
            template = this.templateLeafNode(6, leafNode);
        }

        return `
            <div class="log-item__left-box">
                ${template}
            </div> 
        `;
    }

    templateLeafNodeMiddle(leafNode) {
        return `
            <div class="log-item__mid-box">
                ${this.templateLeafNode(leafNode.data.direction, leafNode)}
            </div>
        `;
    }

    templateLeafNodeRight(leafNode) {

        if (leafNode.data.childLeafIds.length <= 1) {
            return ``; // 자식이 1개 이하인 경우 그릴 필요 없음
        }

        let template = '';
        if (leafNode.data.direction === 2) {
            template = this.templateLeafNode(4, leafNode);
        }

        if (leafNode.data.direction === 3) {
            template = this.templateLeafNode(4, leafNode);
        }

        if (leafNode.data.direction === 6) {
            template = this.templateLeafNode(8, leafNode);
        }

        if (leafNode.data.direction === 7) {
            template = this.templateLeafNode(8, leafNode);
        }

        return `
            <div class="log-item__right-box">
                ${template}
            </div>
        `;
    }

    templateLeafNode(direction, leafNode) {
        let template = '';
        if (direction === 0) {
            template = `
                <div class="log-item-box leaf-item"">
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left hidden"></div>
                    </div>
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top hidden"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot hidden"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch">
                            <div class="log-item__line-right hidden"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title"">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 1) {
            template = `
                <div class="log-item-box leaf-item"">

                    <div class="log-item__left-branch">
                        <div class="log-item__line-left hidden"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top hidden"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch">
                            <div class="log-item__line-right hidden"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 2) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left hidden"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top hidden"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 3) {
            template = `
                <div class="log-item-box leaf-item">
                
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left"></div>
                    </div>
                    
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top hidden"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 4) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top hidden"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch hidden">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 5) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left hidden"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch hidden">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p> 
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 6) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left hidden"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 7) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 8) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch hidden">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 9) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left hidden"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top hidden"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot hidden"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 10) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top hidden"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot hidden"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch hidden">
                            <div class="log-item__line-right hidden"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        if (direction === 11) {
            template = `
                <div class="log-item-box leaf-item">
            
                    <div class="log-item__left-branch">
                        <div class="log-item__line-left hidden"></div>
                    </div>
            
                    <div class="log-item__mid-branch">
                        <div class="log-item__line-top"></div>
                        <div class="log-item__circle node" data-id="${leafNode.data.id}"></div>
                        <div class="log-item__line-bot hidden"></div>
                    </div>
                    <div class="log-item__info-box">
                        <div class="log-item__right-branch hidden">
                            <div class="log-item__line-right"></div>
                        </div>
                        <a class="log-item__link">
                            <div class="log-item__text-box">
                                <p class="log-item__title">${leafNode.data.title}</p>
                                <p class="log-item__date">${leafNode.data.createTime}</p>
                                <ul class="log-item__tags">
                                    ${this.templateLeafNodeTags(leafNode.data.tags)}
                                </ul>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        }

        return  `
            <div class="log-item">
                ${template}
            </div>
        `;
    }

    templateLeafNodeTags (tags) {
        return tags.map(tag => {
            return `<li class="log-item__tag">${tag.name}</li>`;
        }).join('');
    }
}

const focusDate = document.querySelector('section.log-list-date-title-container h1.text--title24');

function findFocusNode() {
    const nodes = document.querySelectorAll('.node');
    const focusY = (window.innerHeight / 2) - 100;
    const focusX = (window.innerWidth / 2) - 100;

    // console.log('start > scroll');
    let minDistance = Number.MAX_SAFE_INTEGER;
    let minNode = null;
    for (let node of nodes) { // 가장 가까운 노드 찾기
        node.classList.remove('node--active');
        const position = node.getBoundingClientRect();
        const distance = this.distance(focusX, focusY, position.x, position.y);
        // console.log(node.dataset.id, distance);

        if (distance < minDistance) {
            minDistance = distance;
            minNode = node;
        }
    }
    // console.log('minNode', minNode);
    minNode.classList.add('node--active');


    const item = minNode.closest('.leaf-item');
    // 화면 날짜 적용
    const dateTag = item.querySelector('.log-item__date');

    if (dateTag.innerText === '') { // 비공개된 리프인 경우
        focusDate.textContent = '비공개된 리프입니다.';
    } else {
        focusDate.textContent = formatDate(dateTag.innerText); // 화면 날짜 적용
        leafRegisterLinkChange(minNode.dataset.id);
    }

    // 브래드 크럼 적용
    const branchCrumbs = document.querySelector('.log-path-box__branch-name');
    const titleTag = item.querySelector('.log-item__title');
    branchCrumbs.innerText = titleTag.innerText;
}

function formatLocalDateTimeToDate(dateString) {
    const [date, time] = dateString.split('T');
    return date;
}

function formatDate(dateString) {
    const [year, month, day] = dateString.split('-');
    return `${year}년 ${month}월 ${day}일`;
}

function leafRegisterLinkChange(leafId) {
    const url = window.location.search;
    const params = new URLSearchParams(url);
    const treeId = params.get('tree_id');
    const link = document.querySelector('a.text__btn--green');
    link.href = `/leaf/reg?tree_id=${treeId}&leaf_id=${leafId}`;
}

// 두점 사이의 거리 함수
function distance(x1, y1, x2, y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
}

function getURLLeafId() {
    const url = window.location.search;
    const params = new URLSearchParams(url);
    return parseInt(params.get('leaf_id'));
}

function focusLeafNode() {
    const leafId = getURLLeafId();
    const node = document.querySelector(`.node[data-id="${leafId}"]`);
    node.scrollIntoView({ behavior: 'smooth', block: 'center' });
}

window.addEventListener('resize', () => {
    const nodes = document.querySelectorAll('.log-item-container');
    for (let node of nodes) {
        if (node.children.length === 1) {
            continue; // 자식이 1개인 경우 스크롤 이벤트 추가하지 않음
        }
        node.style.transform = `translateX(-${node.dataset.index * window.innerWidth}px)`;
    }
});

document.querySelector('body').addEventListener('scroll', () => {
    this.findFocusNode();
});

window.addEventListener('DOMContentLoaded', async () => {
    const leafTree = new LeafTree();
    await leafTree.init();
    focusLeafNode();
});