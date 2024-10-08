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