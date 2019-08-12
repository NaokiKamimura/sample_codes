// Root/test/resources/application.conf
db.default.driver="useDriver"
db.default.url="useUrl"

// Root/test/models/specs2File
import com.typesafe.config.ConfigFactory
// load resources/application.conf
ConfigFactory.load("application.conf")
