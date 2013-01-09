class Scaffold < Thor
  include Thor::Actions
  source_root File.expand_path("../templates", __FILE__)
  def initialize(args=[], options={}, config={})
    super
    self.destination_root= ""
  end

  desc "define_page_md_class", "create domain with class"
  def define_page_md_class
    scaffold_name = "test"
    restful_url = "/#{scaffold_name}.json"
    content = eval( File.read "templates/md_list_class.js" )

    #content = content.gsub "@{scaffold_name}", scaffold_name
    #content = content.gsub "@{restful_url}", "/#{scaffold_name}.json"
    #create_file "public/mobile/js/#{scaffold_name}.js", content
    puts content
  end
end