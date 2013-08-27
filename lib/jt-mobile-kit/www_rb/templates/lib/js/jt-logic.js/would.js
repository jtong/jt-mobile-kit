var would = function(action){
    var result;

    result = {}
    result.action = action;
    result.go = function(){
        this.action.apply(null,this.args);
    }
    result.with = function(){
        this.args = arguments;
        return this;
    }


    result.when = function(condition_or_conditions){
        if(condition_or_conditions.conditions){
            condition_or_conditions.action = this.action;
            condition_or_conditions.action_args = this.args;
            return condition_or_conditions;
        }

        this.condition = condition_or_conditions;
        this.pass = function(){
            if(this.condition()){
                this.action.apply(null,this.args);
            }
        }
        this.not_pass = function(){
            if(!this.condition()){
                this.action.apply(null,this.args);
            }
        }
        return this;
    };
    return result;
}


var not = function(condition){
    return function(){
        return !condition();
    }
}