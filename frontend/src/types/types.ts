export interface LeafTagProps {
  id: Number;
  name: String;
}

export interface LeafItemProps {
  id: Number;
  direction: Number;
  title: String;
  date: String;
  link: String;
  tags: Array<LeafTag>;
}

export interface BeforeLeafItemProps {
  id: Number;
  title: String;
  date: String;
  tags: Array<LeafTag>;
}

export interface CardItemProps {
    cardType: CardType;
    cardImage: string;
    bookCategory: string;
    coverPath: string;

    /* 도서 정보 경우 경우  */
    bookTitle: string;
    bookPublishedYear: string;
    bookAuthor: string;
    bookPublisher: string;

    /* 회고록 정보 */
    treeTitle: string;
    memoirTitle: string;
    title: string;

    /* leaf 카드 정보인 경우 경우  */
    leafTitle: string;
    leafContent: string;

    /* 통계 수  */
    leafCount: number;
    viewCount: number;
    updateTime: string;
}

export interface CardBookPreviewsProps {
    category: string;
    title: string;
    publishDate: string;
    author: string;
    publisher: string;
    link: string;
    createTime: string;
    imageFile: string;
}

export interface BranchInfoProps {
    id: number;
    title: string;
    leafCount: number;
    viewCount: number;
    updateTime: string;
    bookMark: boolean;
}

export interface CardHiddenInfoProps {
    treeDescription: string
}

export interface CardReactNumbersProps {
    leafCount: number,
    likeCount: number,
    viewCount: number,
}

export interface CardProgressBarProps {
    progress: number,
    readPage: number,
    fullPage: number,
}

export interface SnsCardTreeProps {
    cardType: CardType;
    treeTitle: string;
    treeDate: string;
    memoirText: string;
    nickname: string;
    nicknameId: string;
    leafCount: number;
    viewCount: number;
}

export interface FilterItemProps {
    name: string;
    value: number;
    description: string;
}

export interface SeedFilterProps {
    engName: string;
    korName: string;
}

export interface SeedFilterTabProps {
    filterName: string;
    filterItems: SeedFilterProps[];
}

export interface FilterTabProps {
    filterName: string;
    filterItems: FilterItemProps[];
}

export enum Direction {
    INIT,
    START_DOWN,
    START_RIGHT,
    START_SIDE,
    START_LEFT,
    DOWN,
    RIGHT,
    SIDE,
    LEFT,
    END_RIGHT,
    END_LEFT,
    END_UP
}

export enum CardType {
    TREE,
    MEMOIR,
    LEAF
}