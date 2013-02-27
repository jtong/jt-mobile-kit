function JTObject(property_list){
	this.property_list = property_list;
}

JTObject.prototype.each_property = function(handler){
	var me = this;
	for(var index in this.property_list){
		var key = this.property_list[index];
		handler(me, key);
	}
}