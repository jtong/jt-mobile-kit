class WwwRb < Thor
  include Thor::Actions
  source_root File.expand_path("../", __FILE__)
  def initialize(args=[], options={}, config={})
    super
    self.destination_root= ""
  end

  desc :init, "init www folder"
  def init
    directory :templates, :www
  end

  desc :new, "new web project folder"
  def new name
    directory :templates, name.to_sym
  end
end
