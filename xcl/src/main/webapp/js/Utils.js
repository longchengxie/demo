var _Utils = {};
_Utils.setComboxDataSource = function(url, id) {
	$.get(url, function(data) {
		for ( var i in data) {
			$('#' + id).append(
					'<option value="' + data[i].value + '">' + data[i].label
							+ '</option>');
		}
	});
};

/**
 * 数组转json
 * @param o
 * @returns
 */
_Utils.arrayToJson = function (o) { 
	var r = []; 
	if (typeof o == "string"){ 
		return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\""; 
	}
	if (typeof o == "object") { 
		if (!o.sort) { 
			for (var i in o) {
				r.push(i + ":" + Utils.arrayToJson(o[i])); 
			}
			if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) { 
				r.push("toString:" + o.toString.toString()); 
			} 
			r = "{" + r.join() + "}"; 
		} else { 
			for (var i = 0; i < o.length; i++) { 
				r.push(Utils.arrayToJson(o[i])); 
			} 
			r = "[" + r.join() + "]"; 
		} 
		return r; 
	} 
	return o.toString(); 
};

window.Utils = _Utils;