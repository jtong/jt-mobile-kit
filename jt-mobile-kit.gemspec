# -*- encoding: utf-8 -*-
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'jt-mobile-kit/version'

Gem::Specification.new do |gem|
  gem.name          = "jt-mobile-kit"
  gem.version       = Jt::Mobile::Kit::VERSION
  gem.authors       = ["jtong"]
  gem.email         = ["jtong.kata@gmail.com"]
  gem.description   = %q{ description}
  gem.summary       = %q{summary}
  gem.homepage      = ""

  gem.files         = Dir['CHANGELOG.md', 'README.rdoc', 'bin/**/*', 'lib/**/{*,.[a-z]*}']#`git ls-files`.split($/)
  #gem.executables   = gem.files.grep(%r{^bin/}).map{ |f| File.basename(f) }
  gem.bindir             = 'bin'
  gem.executables        = ['jt','jt_android', 'jt_js', 'jt_www_js', 'jt_www_rb']
  gem.test_files    = gem.files.grep(%r{^(test|spec|features)/})
  gem.require_paths = ["lib"]
end
