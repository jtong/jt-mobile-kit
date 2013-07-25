require 'erb'

class GenView < Thor::Group
  include Thor::Actions
  source_root File.expand_path("../templates", __FILE__)
  def initialize(args=[], options={}, config={})
    super
    self.destination_root= ""
  end

  argument :name

  def gen
    page_name = name
    content = ERB.new File.read("#{GenView.source_root}/view.haml.erb")
    create_file "hamls/pages/#{page_name}_page.haml", content.result(binding)
  end

end