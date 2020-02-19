// Javascript
import 'core-js/shim'

// ClassList
import 'classlist-polyfill'

// window.requestAnimationFrame
import 'raf/polyfill'

// Axios
import Axios from 'axios'

// IE 하위 버전에서는 response.data를 가지고 있지 않으므로, 직접 data 속성에 채워준다.
(function () {
    // @ts-ignore
    function createData(response) {
        if (response.request.responseText) {
            switch (response.config.responseType) {
                case 'json':
                    return JSON.parse(response.request.responseText);
            }
        }
    }

    Axios.interceptors.response.use(
        response => {
            if (!response.data) {
                response.data = createData(response);
            }
            return response;
        },
        error => {
            let response = error.response;
            if (response && !response.data) {
                response.data = createData(response);
            }
            throw error;
        }
    );
})();

// IE 하위 버전에서 사용하는 DOM3 기반의 Event는 직접 생성자를 부를 수 없으므로, 별도의 생성 방식을 지원하도록 한다.
// @ts-ignore
(function () {
    function createEventType() {
        const EventType = function (type: string, params: { bubbles: any; cancelable: any; }) {
            let event = document.createEvent('Event');
            params = params || {bubbles: false, cancelable: false};
            event.initEvent(type, params.bubbles, params.cancelable);
            return event;
        };

        EventType.prototype = window.Event.prototype;

        return EventType;
    }

    if (typeof window.Event === "function") {
        return false;
    } else {
        // @ts-ignore
        window.Event = createEventType();
    }
})();

// IE 하위 버전에서 window.location.origin가 존재하지 않으므로 지원하도록 한다.
(function() {
    if (!window.location.origin) {
        const location = window.location;
        // @ts-ignore
        window.location.origin = location.protocol + "//" + location.hostname + (location.port && ":" + location.port);
    }
})();
