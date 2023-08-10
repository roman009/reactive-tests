### MicroK8s
```
microk8s status --wait-ready

microk8s --help
```

### Infra
```
microk8s kubectl create namespace sales-app

microk8s kubectl apply -f infra/ingress-service.yaml

microk8s helm upgrade --install infra ./infra --namespace sales-app --dry-run --debug

helm --kubeconfig .microk8s.config upgrade --install infra ./infra/helm/infra --namespace sales-app

helm --kubeconfig .microk8s.config upgrade --install apps ./infra/helm/apps --namespace sales-app

```

### Enabling MetalLB

Enter each IP address range delimited by comma : 192.168.64.95-192.168.64.100

```
nslookup 192.168.64.95

Server:		192.168.0.249
Address:	192.168.0.249#53

95.64.168.192.in-addr.arpa	name = sales-app.k8s.local.
```