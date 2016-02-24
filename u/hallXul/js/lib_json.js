function JSON() {}
JSON.Array = function (obj) {
    var a = ['['], b, i, l = obj.length, v;
    for (i = 0; i < l; i += 1) {
        v = obj[i];
        switch (typeof v) {
        case 'undefined':
        case 'function':
        case 'unknown':
            break;
        default:
            if (b) {
                a.push(',');
            }
            a.push(v === null ? "null" : JSON.toString(v));
            b = true;
        }
    }
    a.push(']');
    return a.join('');
};

JSON.Boolean = function (obj) {
    return String(obj);
};

JSON.Date = function (obj) {

    function f(n) {
        return n < 10 ? '0' + n : n;
    }

    return '"' + obj.getFullYear() + '-' +
            f(obj.getMonth() + 1) + '-' +
            f(obj.getDate()) + 'T' +
            f(obj.getHours()) + ':' +
            f(obj.getMinutes()) + ':' +
            f(obj.getSeconds()) + '"';
};

JSON.Number = function (obj) {
    return isFinite(obj) ? String(obj) : "null";
};

JSON.Object = function (obj) {
    var a = ['{'], b, i, v;
    for (i in obj) {
        if (obj.hasOwnProperty(i)) {
            v = obj[i];
            switch (typeof v) {
            case 'undefined':
            case 'function':
            case 'unknown':
                break;
            default:
                if (b) {
                    a.push(',');
                }
                a.push(JSON.toString(i), ':',
                        v === null ? "null" : JSON.toString(v));
                b = true;
            }
        }
    }
    a.push('}');
    return a.join('');
};

JSON.String = function (obj) {
        var m = {
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        };

        if (/["\\\x00-\x1f]/.test(obj)) {
            return '"' + obj.replace(/([\x00-\x1f\\"])/g, function(a, b) {
                var c = m[b];
                if (c) {
                    return c;
                }
                c = b.charCodeAt();
                return '\\u00' +
                    Math.floor(c / 16).toString(16) +
                    (c % 16).toString(16);
            }) + '"';
        }
        return '"' + obj + '"';
};


JSON.toString = function(o){
    var obj;
    //console.log(o, typeof o)
   if (typeof o == 'object' && o.constructor == Array){
        obj = JSON.Array(o);
   } else if(typeof o == 'boolean'){
        obj = JSON.Boolean(o);
   } else if(typeof o == 'object' && o.constructor == Date){
        obj = JSON.Date(o);
   } else if(typeof o == 'number'){
        obj = JSON.Number(o);
   } else if(typeof o == 'string'){
        obj = JSON.String(o);
   } else if (typeof o == 'function') {
           obj = ""; /*do not serialize functions*/
   } else if(typeof o == 'object'){
        obj = JSON.Object(o);
   } else {
           console.log("IMPOSSIBLE ????", o);
   }
   return obj

}