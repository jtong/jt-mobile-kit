function build_action(name) {
    var action =  function(){
        for(var i = 0; i < action.listeners.length; i++){
           try{
               action.listeners[i](action._target);
           } catch(e){
             console.debug(e);
           }
        }
    }

    action.with = function(target){
        action._target = target;
        return action;
    }

    action.notify = function(){
        action.listeners = arguments;
        return action;
    }

    return action;
}