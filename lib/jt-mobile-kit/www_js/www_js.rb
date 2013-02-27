class WwwJs < Thor
  include Thor::Actions
  source_root File.expand_path("../", __FILE__)
  def initialize(args=[], options={}, config={})
    super
    self.destination_root= ""
  end

  desc "init", "create js based www folder"
  def init
    directory :templates, :www
  end
end

