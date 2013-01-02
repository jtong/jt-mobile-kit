class Android < Thor
  include Thor::Actions
  source_root File.expand_path("../", __FILE__)
  def initialize(args=[], options={}, config={})
    super
    self.destination_root= ""
  end

  desc "init", "create cordova based android project"
  def init
    directory :templates, :app
  end
end

