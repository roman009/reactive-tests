### MicroK8s

microk8s status --wait-ready

microk8s --help

### docker build

docker build -t localhost:32000/inventory:v1 -f inventory/Dockerfile .

microk8s kubectl create namespace sales-app

microk8s helm upgrade --install infra ./infra --namespace sales-app --dry-run --debug
helm --kubeconfig ../../.microk8s.config upgrade --install infra ./infra --namespace sales-app