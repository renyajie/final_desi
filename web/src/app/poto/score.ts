/**
 * 课程评价
 */
export class Score {
    constructor(
        public id?: number,
        public uId?: number,
        public uName?: string,
        public claId?: number,
        public claKId?: number,
        public classKName?: string,
        public pId?: number,
        public pName?: string,
        public score?: number,
        public comment?: number,
        public scoreTime?: string,
        public orderId?: number,
        public orderTime?: number,
        public age?: number,
        public gender?: string
    ) {}

    static fromJSON(json: any): Score {
        const score = Object.create(Score.prototype);
        Object.assign(score, json);
        return score;
    }
}