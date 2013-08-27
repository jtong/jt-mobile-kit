var all = function(){
    var result = new AllConditions();
    result.init(arguments);
    return result;
}
var any = function(){
    var result = new AnyConditions();
    result.init(arguments);
    return result;
}

function Conditions(){
    this.conditions = [];
}

Conditions.prototype.init = function(args){
    if(args.length == 1){
        this.conditions = [].concat(args[0])
    } else{
        this.conditions = args;
    }
}
Conditions.prototype.go_through_conditions = function(all_pass_judge, is_if_break_fail, break_flag) {
    var conditions = this.conditions;
    for (var index in conditions) {
        var condition = conditions[index];
        var current_pass = condition.apply(null, condition.self_params);
        all_pass_judge(current_pass);
        if (!current_pass && condition.not_pass_handler) {
            condition.not_pass_handler.apply(null, condition.not_pass_args);
        }
        if (current_pass && condition.pass_handler) {
            condition.pass_handler.apply(null, condition.pass_args);
        }
        if(is_if_break_fail && current_pass == break_flag){
            break;
        }
    }
}

function AllConditions(){

}

AllConditions.prototype = new Conditions();

AllConditions.prototype.pass = function(){
    var all_pass  = true;
    var all_pass_judge = function(current_condition_pass){
        all_pass = all_pass && current_condition_pass;
        return all_pass;
    }
    this.go_through_conditions(all_pass_judge);
    if(all_pass){
        this.action.apply(null, this.action_args);
    };
}

AllConditions.prototype.pass_and_break_first = function(){
    var all_pass  = true;
    var all_pass_judge = function(current_condition_pass){
        all_pass = all_pass && current_condition_pass;
        return all_pass;
    }
    this.go_through_conditions(all_pass_judge, true, false);
    if(all_pass){
        this.action.apply(null, this.action_args);
    };
}

AllConditions.prototype.not_pass_and_break_first = function(){
    var all_pass  = false;
    var all_pass_judge = function(current_condition_pass){
        all_pass = all_pass || current_condition_pass;
        return all_pass;
    }
    this.go_through_conditions(all_pass_judge,true, true);

    if(!all_pass){
        this.action.apply(null, this.action_args);
    };
}

AllConditions.prototype.not_pass = function(){
    var all_pass  = false;
    var all_pass_judge = function(current_condition_pass){
        all_pass = all_pass || current_condition_pass;
        return all_pass;
    }
    this.go_through_conditions(all_pass_judge);

    if(!all_pass){
        this.action.apply(null, this.action_args);
    };
}


function AnyConditions(){
    
}

AnyConditions.prototype = new Conditions();

AnyConditions.prototype.pass = function(){
    var any_pass  = false;
    var any_pass_judge = function(current_condition_pass){
        any_pass = any_pass || current_condition_pass;
        return any_pass;
    }
    this.go_through_conditions(any_pass_judge);
    if(any_pass){
        this.action.apply(null, this.action_args);
    };
}

AnyConditions.prototype.pass_and_break_first = function(){
    var any_pass  = false;
    var any_pass_judge = function(current_condition_pass){
        any_pass = any_pass || current_condition_pass;
        return any_pass;
    }
    this.go_through_conditions(any_pass_judge, true, false);
    if(any_pass){
        this.action.apply(null, this.action_args);
    };
}

AnyConditions.prototype.not_pass = function(){
    var any_pass  = true;
    var any_pass_judge = function(current_condition_pass){
        any_pass = any_pass && current_condition_pass;
        return any_pass;
    }
    this.go_through_conditions(any_pass_judge);
    if(!any_pass){
        this.action.apply(null, this.action_args);
    };
}

AnyConditions.prototype.not_pass_and_break_first = function(){
    var any_pass  = true;
    var any_pass_judge = function(current_condition_pass){
        any_pass = any_pass && current_condition_pass;
        return any_pass;
    }
    this.go_through_conditions(any_pass_judge, true, true);
    if(!any_pass){
        this.action.apply(null, this.action_args);
    };
}