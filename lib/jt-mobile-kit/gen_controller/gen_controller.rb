require 'erb'

class GenController < Thor::Group
  include Thor::Actions
  source_root File.expand_path("../templates", __FILE__)
  def initialize(args=[], options={}, config={})
    super
    self.destination_root= ""
  end

  argument :name

  def gen
    controller_name = name
    content = ERB.new File.read("#{GenController.source_root}/controller.js.erb")
    create_file "js/controller/#{controller_name}.js", content.result(binding)
  end
end