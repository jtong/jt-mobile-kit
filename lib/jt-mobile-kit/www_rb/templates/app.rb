ENV['RACK_ENV'] ||= "development"

require 'sinatra'
require 'haml'
require 'moode-haml-toolkit/component_helper'
require 'jt-partial'
if File.exist? "component/custom.rb"
  load "component/custom.rb"
end
require 'moode-haml-toolkit/component_generator'

require 'moode-haml-component-angular'


configure do
  set :views, File.join(File.dirname(__FILE__), 'hamls')
  set :public_folder, File.dirname(__FILE__)
end

get "/" do
  haml :index
end

get "/pages/:name.html" do
  haml "pages/#{params[:name]}".to_sym
end