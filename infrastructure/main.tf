provider "azurerm" {}

# Make sure the resource group exists
resource "azurerm_resource_group" "rg" {
  name     = "${var.product}-${var.component}-${var.env}"
  location = "${var.location_app}"
}

locals {
  ase_name               = "${data.terraform_remote_state.core_apps_compute.ase_name[0]}"
}

module "feature-toggle-web-console" {
  source              = "git@github.com:hmcts/moj-module-webapp?ref=master"
  product             = "${var.product}-${var.component}"
  location            = "${var.location_app}"
  env                 = "${var.env}"
  ilbIp               = "${var.ilbIp}"
  resource_group_name = "${azurerm_resource_group.rg.name}"
  subscription        = "${var.subscription}"
  is_frontend         = true
  additional_host_name = "www.feature-toggle.saat.platform.hmcts.net"

  app_settings = {
  }
}
