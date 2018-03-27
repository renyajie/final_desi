/**
 * 通知
 */
export class News {
    constructor(
        public id?: number,
        public mId?: number,
        public mName?: string,
        public pId?: number,
        public pName?: string,
        public title?: string,
        public browTime?: number,
        public pubTime?: string,
        public context?: string
    ) {}

    static fromJSON(json: any): News {
        const news = Object.create(News.prototype);
        Object.assign(news, json);
        return news;
    }
}