require 'erb'

class GenRouting < Thor::Group
  include Thor::Actions
  source_root File.expand_path("../templates", __FILE__)
  def initialize(args=[], options={}, config={})
    super
    self.destination_root= ""
  end

  argument :name

  def gen
    page_name = name
    controller_name = name
    content = ERB.new File.read("#{GenRouting.source_root}/routing.js.erb")
    inject_into_file "js/routes.js",content.result(binding), :after => "//routing generate"
  end

end