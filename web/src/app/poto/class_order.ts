/**
 * 课程订单
 */
export class ClassOrder {
    constructor(
        public id?: number,
        public pId?: number,
        public pName?: string,
        public claId?: number,
        public claKName?: string,
        public uId?: number,
        public uName?: string,
        public cardId?: number,
        public cardKName?: string,
        public ordTime?: string,
        public num?: string
    ) {}

    static fromJSON(json: any): ClassOrder {
        const classOrder = Object.create(ClassOrder.prototype);
        Object.assign(classOrder, json);
        return classOrder;
    }

    toString() {
        return `
        ClassOrder {
            id: ${this.id},
            pId: ${this.pId},
            pName: ${this.pName},
            claId: ${this.claId},
            claKName: ${this.claKName},
            uId: ${this.uId},
            uName: ${this.uName},
            cardId: ${this.cardId},
            cardKName: ${this.cardKName},
            ordTime: ${this.ordTime},
            num: ${this.num}
        }`;
    }
}