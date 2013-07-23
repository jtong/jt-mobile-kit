require 'thor'
require 'jt-mobile-kit/gen_controller/gen_controller'

class JtGenerator < Thor
  include Thor::Actions
  source_root File.expand_path("../", __FILE__)

  register GenController, :controller, "controller", "Generate Controller"
end

class JtMain < Thor
  register JtGenerator, :g, "g", "Generate something"
end



