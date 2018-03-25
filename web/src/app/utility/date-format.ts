export class DateFormat extends Date{

    static dayInMonth = [
        [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], 
        [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    ];

    /**
     * 获取yyyy-MM-dd hh:mm格式的日期
     * @param date 日期
     */
    static format(date: Date) {
        let result = '';
        //先获取UTC时间
        let year = date.getUTCFullYear();
        let month = date.getUTCMonth();
        let day = date.getUTCDate();
        let hour = date.getUTCHours();
        let minute = date.getUTCMinutes();
        let isYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ? 1 : 0;
        //计算本地时间
        hour = hour + 8;
        if(hour >= 24) {
            hour = hour - 24;
            day = day + 1;
            if(day > DateFormat.dayInMonth[isYear][month]){
                day = 1;
                month = month + 1;
                if(month >= 12) {
                    year = year + 1;
                    month = 0;
                }
            }
        }
        //拼接结果
        result = year + '-' + (month + 1) + '-' + day + ' ' + hour + ':' + minute;
        return result;
    }

    /**
     * 获得yyyy-MM-dd格式的日期
     * @param date 日期
     */
    static formatWithDay(date: Date){
        let result = DateFormat.format(date);
        return result.substring(0, result.indexOf(' '));
    }
}