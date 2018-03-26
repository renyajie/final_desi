/**
 * 会员卡信息
 */
export class CardInfo {
    constructor(
        public id?: number,
        public cardKId?: number,
        public cardKName?: string,
        public uId?: number,
        public uName?: string,
        public allowance?: number
    ) {}

    static fromJSON(json: any): CardInfo {
        const cardInfo = Object.create(CardInfo.prototype);
        Object.assign(cardInfo, json);
        return cardInfo;
    }

    toString() {
        return `
        CardInfo {
            id: ${this.id},
            cardKId: ${this.cardKId},
            cardKName: ${this.cardKName},
            uId: ${this.uId},
            uName: ${this.uName},
            allowance: ${this.allowance}
        }`;
    }
}