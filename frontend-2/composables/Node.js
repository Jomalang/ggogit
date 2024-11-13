import { reactive, ref } from "vue";

export class Node {

    constructor(data, seedType) {
        // console.log("data", data);
        this._data = reactive(data);
        this._parent = ref({});
        this._children = reactive([]);
        this._focus = data.focused;
        this._translateIndex = 0;
        this._seedType = seedType;
        this.translateIndexInit();
    }

    get parentLeafId() {
        return this._data.parentLeafId;
    }

    get translateIndex() {
        return this._translateIndex;
    }

    set translateIndex(index) {
        this._translateIndex = index;
    }

    get direction() {
        return this._data.direction;
    }

    get childLength() {
        return this._data.childLeafIds.length;
    }

    get getSwipeChildId() {
        return this._data.childLeafIds[this._translateIndex];
    }

    set focus(focus) {
        this._focus = focus;
    }

    // 브랜치를 생성 가능한지
    get isCreateBranch() {
        return this._data.childLeafIds.length < 3;
    }

    get link() {
        return `/leaf/${this._seedType}/${this._data.id}`;
    }

    get leftData() {

        const directionMap = {
            3: 2,
            7: 6
        };
        let direction = directionMap[this.direction] ?? this.direction;

        return {
            id: this._data.id,
            direction: direction,
            title: this._data.title,
            date: this._data.createTime,
            link: this.link,
            tags: this._data.tags,
            focus: this._focus,
        };
    }

    get midData() {
        return {
            id: this._data.id,
            direction: this._data.direction,
            title: this._data.title,
            date: this._data.createTime,
            link: this.link,
            tags: this._data.tags,
            focus: this._focus,
        };
    }

    get rightData() {

        const directionMap = {
            2: 4,
            3: 4,
            6: 8,
            7: 8
        };
        let direction = directionMap[this.direction] ?? this.direction;

        return {
            id: this._data.id,
            direction: direction,
            title: this._data.title,
            date: this._data.createTime,
            link: this.link,
            tags: this._data.tags,
            focus: this._focus,
        };
    }

    get isLeft() {
        // console.log("isLeft node Id", this._data.id, "childLeafIds", this._data.childLeafIds);
        if (this._data.childLeafIds.length !== 3) {
            return false; // 자식이 3개가 아닌경우 왼쪽 리프 그릴 필요 없음
        }
        return true;
    }

    get isRight() {
        // console.log("isRight node Id", this._data.id, "childLeafIds", this._data.childLeafIds);
        if (this._data.childLeafIds.length <= 1) {
            return false; // 자식이 1개 이하인 경우 오른쪽 리프 그릴 필요 없음
        }
        return true;
    }

    get id() {
        return this._data.id;
    }

    get edgeCheck() {
        // console.log("edgeCheck node Id", this._data.id, "this._children", this._children);
        return 0 <= this._children.length && this._children.length <= 2;
    }

    get children() {
        return this._children;
    }

    get childLeafIds() {
        return this._data.childLeafIds;
    }

    translateIndexInit() {
        // console.log("this._data", this._data);
        if (this._data.childLeafIds.length === 3) {
            this._translateIndex = 1;
        } else {
            this._translateIndex = 0;
        }
    }

    translateSize(screenWidth) {
        // console.log("translateSize screenWidth", screenWidth, "this._translateIndex", this._translateIndex);
        return screenWidth * this._translateIndex;
    }

    addChild(node) {
        // console.log("addChild node 자식 추가", node);
        this._children.push(node);
    }

    initParent(parent) {
        // console.log("initParent parent 부모 초기화", parent);
        this._parent.value = parent;
    }

    hasChildren() {
        return this._children.length > 0;
    }
}