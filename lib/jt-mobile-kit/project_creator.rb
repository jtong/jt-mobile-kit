require "thor"
require "thor/group"

class ProjectCreator < Thor::Group
  include Thor::Actions
  source_root File.expand_path("../root_templates", __FILE__)

  argument :project_name

  def initialize(args=[], options={}, config={})
    super
    self.destination_root= ""
  end

  def create_project
    empty_directory project_name
  end

  def create_gitignore
    copy_file "gitignore", "#{project_name}/.gitignore"
  end

  def create_www_folder
    inside project_name do
      run "jt_www_rb init"
    end
  end

  def create_app_project
    inside project_name do
      run "jt_android init"
    end
  end

  def create_server_project
    inside project_name do
      run "rails new server"
    end
  end

  def create_server_project_mobile_web_soft_link
    inside "#{project_name}/server/public" do
      run "ln -s ../../app/assets/www/ mobile"
    end
  end
end
