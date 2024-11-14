import { reactive, ref } from "vue";
import { Node } from "./Node.js";
import axios from "axios";

const config = useRuntimeConfig();

export class Tree {

    constructor(initialData, seedType) {
        this.root = ref({});
        this.nodes = reactive([]);
        this.edgeNodes = reactive([]);
        this._seedType = seedType;
        this.addNodeAll(initialData.items);
    }

    initRoot(node) {
        this.root.value = node;
        this.nodes.push(node);
        this.edgeNodes.push(node);
    }

    addNodeAll(data) {
        for (let item of data) {
            // console.log("item 노드 데이터 추가", item.id, item);
            let node = new Node(item, this._seedType); // 노드 객체로 변환
            this.addNode(node); // 노드를 추가하면서 트리 생성
        }
    }

    addNode(node) {
        // console.log("addNode Call : node", node);
        // console.log("this.root", this.root, "node.data.parentLeafId", node.parentLeafId);

        if (node.parentLeafId === null) {
            // console.log("ROOT 등록 완료"); // 루트 데이터 추가
            this.initRoot(node);
            return; // 루트 노드 추가 후 종료
        }

        // 일반 리프 데이터 추가

        let parentNode = this.findParentNode(node); // console.log("parentNode", parentNode); // << 여기 진행중
        node.initParent(parentNode); // 자식 노드에 부모 노드 추가
        this.nodes.push(node); // 전체 노드에 자식 노드 추가

        if (parentNode.edgeCheck) {
            // console.log("엣지 노드 추가");  // 엣지 노드인 경우 추가
            this.edgeNodes.push(node);
        } else {
            // console.log("엣지 노드 추가 X");
            this.edgeNodes = this.edgeNodes // 엣지 노드가 아닌 경우 제거
                .filter(edgeNode => edgeNode.id !== parentNode.id);
        }

        parentNode.addChild(node); // 부모 노드에 자식 노드 추가
    }

    findParentNode(node) { // console.log("this.edgeNodes", this.edgeNodes);
        for (let edgeNode of this.edgeNodes) {
            // console.log("edgeNode", edgeNode);
            // console.log(edgeNode.id, node.parentLeafId);
            if (edgeNode.id === node.parentLeafId) {
                return edgeNode;
            }
        }
        throw new Error("부모 노드를 찾을 수 없습니다.");
    }

    /**
    * ROOT 노드부터 하단 End 노드까지 모두 조회
    * */
    getNodeAll(leafId) {
        let nodes = [];

        // 부모 노드 찾기
         // console.log("부모 노드 찾기 시작");
        this.findToRoot(leafId, nodes);
        nodes.reverse(); // 순서 뒤집기

        // 자식 노드 찾기
         // console.log("자식 노드 찾기 시작");
        this.findToEnd(leafId, nodes);

         // console.log("nodes", nodes);
        return nodes;
    }

    /**
     * LeafId 부터 End 노드까지 조회
     * */
    async getNodeToEnd(leafId) {
         // console.log("LeafId 부터 End 노드까지 조회", leafId);
        let nodes = [];

        if (!this.hasNode(leafId)) { // 노드가 존재하지 않는 경우 API 호출
             // console.log("노드가 존재하지 않는 경우 API 호출");
            await this.fetchNode(leafId);
        }

        // 자신의 노드 넣기
        let node = this.findNode(leafId);
        nodes.push(node);

        // 자식 노드 찾기
         // console.log("자식 노드 찾기 시작");
        this.findToEnd(leafId, nodes);

         // console.log("nodes", nodes);
        return nodes;
    }

    /**
     * API 호출
     * */
    async fetchNode(leafId) {
        try {
            const response = await axios.get(`${config.public.apiBase}/leaves/${leafId}/end`);
            const data = response.data;
             // console.log("data 새로운 데이터 fetch", data.items);
            this.addNodeAll(data.items);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }

    /**
     * 노드 존재 확인
     * */
    hasNode(leafId) {
        for (let node of this.nodes) {
            if (node.id === leafId) {
                return true;
            }
        }
        return false;
    }

    /**
     * 현재 노드에서 ROOT 노드까지 조회
     * */
    findToRoot(leafId, nodes) {
        let node = this.findNode(leafId);

        // // console.log("findToRoot 시작", node.id);
        if (node.hasChildren() && 1 <= nodes.length) { // 브랜치가 역으로 올라갈 때 translateIndex 설정
            let childNode = nodes[nodes.length - 1];
            // // console.log("childNode", childNode);
            for (let i = 0; i < node.childLeafIds.length; i++) {
                if (node.childLeafIds[i] === childNode.id) {
                    node.translateIndex = i;
                    break;
                }
            }
        }

        nodes.push(node);
        if (node.parentLeafId === null) {
            return; // 부모인 경우 종료
        }

        this.findToRoot(node.parentLeafId, nodes);
    }

    /**
     * 현재 노드에서 End 노드까지 조회
     * */
    findToEnd(leafId, nodes) {
        let node = this.findNode(leafId);

         // console.log("findToEnd 시작", node);
         // console.log("node.hasChildren()", node.hasChildren());
        while (node.hasChildren()) {
            let childNodes = node.children;
             // console.log("childNodes", childNodes);
            if (childNodes.length === 1) {
                node = childNodes[0];
            }
            else if (childNodes.length === 2) {
                node = childNodes[0];
            }
            else if (childNodes.length === 3) {
                node = childNodes[1];
            }
            nodes.push(node);
        }
    }

    isCreateBranch(leafId) {
         // console.log("isCreateBranch", leafId);
        let node = this.findNode(leafId);
        return node.isCreateBranch;
    }

    findNode(leafId) {
         // console.log("노드 리스트", this.nodes);

        leafId = Number(leafId);
        for (let node of this.nodes) {
            if (node.id === leafId) {
                return node;
            }
        }
        // // console.log("노드를 찾을 수 없습니다.", leafId);
        throw new Error("노드를 찾을 수 없습니다.");
    }

    async getBranchInfo(swipeChildId) {
        try {
             // console.log("브랜치 조회 API 호출", swipeChildId);
            const response = await axios.get(`${config.public.apiBase}/leaves/${swipeChildId}/branch`);
            return response.data;
             // console.log("data 새로운 데이터 fetch", data.items);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }
}