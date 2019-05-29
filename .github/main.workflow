workflow "New workflow" {
  on = "pull_request"
  resolves = ["Run MVN tests"]
}

action "filter-to-pr-open-synced" {
  uses = "actions/bin/filter@master"
  args = "action 'opened|synchronize'" 
}

action "Run MVN tests" {
  needs = ["filter-to-pr-open-synced"]
  uses = "actions/bin/sh@master"
   args = ["mvnw test"]
}
