var this_root = this;

function __define_jtclass(class_name){
	var define_code = ["function ", class_name, "(property_list){ JTObject.apply(this, arguments); }",class_name,".prototype=new JTObject();"].join('');
	this_root.eval(define_code);
}

function __define_each_property_method_handler(class_name, method_define_map){
    for (var method_handler_name in method_define_map){
        var define_code = [class_name, ".prototype.",method_handler_name,"=function(){",
            "this.each_property(", method_define_map[method_handler_name].toString(),");",
            "return this;",
            "}"
        ].join("");
        //console.log(define_code);
        eval(define_code);
    }

}
