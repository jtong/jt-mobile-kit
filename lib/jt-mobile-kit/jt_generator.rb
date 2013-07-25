require 'thor'
require 'jt-mobile-kit/gen_controller/gen_controller'
require 'jt-mobile-kit/gen_view/gen_view'
require 'jt-mobile-kit/gen_routing/gen_routing'


class JtGenerator < Thor
  include Thor::Actions
  source_root File.expand_path("../", __FILE__)

  register GenController, :controller, "controller", "Generate Controller"
  register GenView, :view, "view", "Generate view"
  register GenRouting, :routing, "routing", "Generate routing"

  desc :scaffold, "generate scaffold"
  def scaffold name
    GenController.start [name]
    GenView.start [name]
    GenRouting.start [name]
  end
end



class JtMain < Thor
  register JtGenerator, :g, "g", "Generate something"
end



