/**
 * 场馆管理员
 */
export class Manager {
    constructor(
        public account?: string,
        public passwd?: string,
        public id?: number,
        public phone?: string,
        public mName?: string,
        public gender?: string,
        public pId?: number,
        public sName?: string
    ) {}

    static fromJSON(json: any): Manager {
        const manager = Object.create(Manager.prototype);
        Object.assign(manager, json);
        return manager;
    }

    toString() {
        return `
        Manager {
            id: ${this.id},
            phone: ${this.phone},
            account: ${this.account},
            passwd: ${this.passwd},
            mName: ${this.mName},
            gender: ${this.gender},
            pId: ${this.pId}
        }`;
    }
}