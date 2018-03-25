/**
 * 会员卡种类
 */
export class CardKind {
    constructor(
        public id?: number,
        public pId?: number,
        public pName?: string,
        public cardKName?: string,
        public capacity?: number,
        public expend?: number
    ) {}

    static fromJSON(json: any): CardKind {
        const cardKind = Object.create(CardKind.prototype);
        Object.assign(cardKind, json);
        return cardKind;
    }

    toString() {
        return `
        CardKind {
            id: ${this.id},
            pId: ${this.pId},
            pName: ${this.pName},
            cardKName: ${this.cardKName},
            capacity: ${this.capacity},
            expend: ${this.expend}
        }`;
    }
}