export enum MessageType {
    Enter,
    Talk,
}

export interface Message {
    content: string;
    type: MessageType;
    time: Date;
    sender: string;
}