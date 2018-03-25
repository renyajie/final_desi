/**
 * 购卡订单
 */
export class CardOrder {
    constructor(
        public id?: number,
        public uId?: number,
        public uName?: string,
        public cardKId?: number,
        public cardKName?: string,
        public ordTime?: string,
        public cardId?: string
    ) {}

    static fromJSON(json: any): CardOrder {
        const cardOrder = Object.create(CardOrder.prototype);
        Object.assign(cardOrder, json);
        return cardOrder;
    }

    toString() {
        return `
        CardOrder {
            id: ${this.id},
            uId: ${this.uId},
            uName: ${this.uName},
            cardKId: ${this.cardKId},
            cardKName: ${this.cardKName},
            ordTime: ${this.ordTime},
            cardId: ${this.cardId}
        }`;
    }
}