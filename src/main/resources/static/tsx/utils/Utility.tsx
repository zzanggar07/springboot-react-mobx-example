import * as Moment from "moment/moment";

import MomentDurationFormatSetup from "moment-duration-format";
import {isIE, isMobile} from "react-device-detect"

MomentDurationFormatSetup(Moment);

/**
 * Checks whether we're running on a production build or not.
 */
export const isProduction = process.env.NODE_ENV === 'production';

/**
 * Checks whether a variable is defined.
 *
 * @param {*} variable Variable to check
 */
export const isDefined = (variable: any) => typeof variable !== 'undefined';

/**
 * Checkes whether a variable provided is a string.
 *
 * @param {*} variable Variable to check
 */
export const isString = (variable: any) => typeof variable === 'string';

/**
 * Checks whether a variable provided is an ArrayBuffer.
 *
 * @param {*} variable Variable to check
 */
export const isArrayBuffer = (variable: any) => variable instanceof ArrayBuffer;

const consoleOnDev = (method: string, ...message: any[]) => {
    if (!isProduction) {
        // eslint-disable-next-line no-console
        // @ts-ignore
        console[method](...message);
    }
};

export const warnOnDev = (...message: any[]) => consoleOnDev('warn', ...message);

export const errorOnDev = (...message: any[]) => consoleOnDev('error', ...message);


export const stringFromDate = function (date: string | number | void | Moment.Moment | Date | (string | number)[] | Moment.MomentInputObject | undefined) {
    return Moment(date).locale('ko').format('YYYY.MM.DD');
};

export const stringFromDateTime = function (date: string | number | void | Moment.Moment | Date | Moment.MomentInputObject | (string | number)[] | undefined) {
    return Moment(date).locale('ko').format('YYYY.MM.DD, h:mm:ss a');
};

export const numberWithComma = function (value: any) {
    return Number(value).toLocaleString("en").split(".")[0];
};

export const checkPasswordType = (password: string) => {
    return /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(password);
};

export const checkUserIdType = (userId: string) => {
    return /^[A-Za-z]{1}[A-Za-z0-9]{3,19}$/.test(userId)
};

export const isSupportedVideo = (extension: string) => {
    switch (extension.toLowerCase()) {
        case 'mp4':
        case 'webm':
            return true;
    }
    return false;
};

export const isSupportedAudio = (extension: string) => {
    switch (extension.toLowerCase()) {
        case 'mp3':
        case 'wav':
        case 'ogg':
            return true;
    }
    return false;
};

export const isSupportedImage = (contentType: string) => {
    if (contentType.length === 0) {
        return false;
    }
    switch (contentType.toLowerCase()) {
        case 'image':
            return true;
    }
    return false;
};

export const getSubPathArray = (parent: string | any[], target: string | any[]) => {
    const length = Math.min(parent.length, target.length);
    for (let index = 0; index < length; index++) {
        if (parent[index] === target[index]) {
            continue;
        }
        return target.slice(index);
    }
    return target.slice(length);
};

export const getDuration = (millisecond: string | number | void | Moment.Duration | Moment.FromTo | Moment.DurationInputObject | undefined) => {
    return Moment.duration(millisecond, 'seconds').format('HH:mm:ss');
};

export const getConnectedTime = (connectTime: string | number | void | Moment.MomentInputObject | Moment.Moment | Date | (string | number)[] | undefined) => {
    const start = Moment(connectTime);
    const end = Moment();
    const duration = Moment.duration(end.diff(start));

    return duration.format();
};

// @ts-ignore
export const getChannels = (channel: any) => {
    switch (channel) {
        case '1':
            return 'Mono';
        case '2':
            return 'Stereo';
    }
};

export const isMobileDevice = () => {
    return isMobile
};

export const isEmpty = (value: any) => {
    return value === "" || value === null || value === undefined || (value && typeof value == "object" && !Object.keys(value).length)
};

export const download = (url: string) => {
    let tempLink = (() => {
        let result = document.createElement('a');

        result.style.display = 'none';
        result.href = url;

        if (isIE) {
            result.setAttribute('target', "_blank");
        } else {
            result.setAttribute('download', '');
        }

        return result;
    })();

    document.body.appendChild(tempLink);

    try {
        tempLink.click();
    } finally {
        document.body.removeChild(tempLink);
    }
};

export const phoneNumberMask = (rawValue:string) => {
    let value = rawValue.split('-').join('');
    if (!value.startsWith('0')) {
        return [];
    }
    if (value.length <= 10) {
        return [/\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
    }

    return [/\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
};

export const telNumberMask = (rawValue:string) => {
    let value = rawValue.split('-').join('');

    if (!value.startsWith('0')) {
        return [];
    }

    if (value.startsWith('02')) {
        return value.length === 9 ? [/\d/, /\d/, '-',  /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/] : [/\d/, /\d/, '-',  /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
    } else {
        return value.length === 10 ? [/\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/] : [/\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
    }
};

export const faxNumberMask = (rawValue:string) => {
    return telNumberMask(rawValue)
};

export const checkBisNo =  (bisNo:string) => {
    // 넘어온 값의 정수만 추츨하여 문자열의 배열로 만들고 10자리 숫자인지 확인합니다.
    // @ts-ignore
    if ((bisNo = (bisNo+'').match(/\d{1}/g)).length !== 10) { return false; }

    // 합 / 체크키
    var sum = 0, key = [1, 3, 7, 1, 3, 7, 1, 3, 5];

    // 0 ~ 8 까지 9개의 숫자를 체크키와 곱하여 합에더합니다.
    for (var i = 0 ; i < 9 ; i++) { sum += (key[i] * Number(bisNo[i])); }

    // 각 8번배열의 값을 곱한 후 10으로 나누고 내림하여 기존 합에 더합니다.
    // 다시 10의 나머지를 구한후 그 값을 10에서 빼면 이것이 검증번호 이며 기존 검증번호와 비교하면됩니다.
    return (10 - ((sum + Math.floor(key[8] * Number(bisNo[8]) / 10)) % 10)) === Number(bisNo[9]);
};

export const checkTelNumber = (number:string) => {
    return /^\d{2,3}-\d{3,4}-\d{4}$/.test(number)
};

export const checkPhoneNumber = (number:string) => {
    return  /^\d{3}-\d{3,4}-\d{4}$/.test(number)
};

export const checkEmail = (email:string) => {
    return /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(email)
};

export const getUniqueObjectArray = (array: any[], key: string | number) => {
    return array.filter((item, i) => {
        return array.findIndex((item2, j) => {
            return item[key] === item2[key];
        }) === i;
    });
};
