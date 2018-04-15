/**
 * 瑜伽场馆
 */
export class Place {
    constructor(
        public id?: number,
        public phone?: string,
        public sName?: string,
        public address?: string,
        public picUrl?: string,
        public intro?: string
    ) {}

    static fromJSON(json: any): Place {
        const place = Object.create(Place.prototype);
        Object.assign(place, json);
        return place;
    }
    
}