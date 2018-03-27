/**
 * 系统管理员
 */
export class SystemManager {
    constructor(
        public account?: string,
        public passwd?: string,
        public id?: number,
        public phone?: string,
        public sName?: string,
        public gender?: string
    ) {}

    static fromJSON(json: any): SystemManager {
        const sysManager = Object.create(SystemManager.prototype);
        Object.assign(sysManager, json);
        return sysManager;
    }

    toString() {
        return `
        SystemManager {
            id: ${this.id},
            phone: ${this.phone},
            account: ${this.account},
            passwd: ${this.passwd},
            sName: ${this.sName},
            gender: ${this.gender}
        }`;
    }
}
