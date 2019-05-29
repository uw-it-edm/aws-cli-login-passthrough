workflow "New workflow" {
  on = "pull_request"
  resolves = ["Run MVN tests"]
}

action "Filter opened and synchronize events" {
  uses = "actions/bin/filter@master"
  args = "action 'opened|synchronize'" 
}


action "Run MVN tests" {
  needs = ["Filter opened and synchronize events"]
  uses = "actions/bin/sh@master"
   args = ["./mvnw test"]
}
