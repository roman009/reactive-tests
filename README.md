### MicroK8s
```
microk8s status --wait-ready

microk8s --help

microk8s status
microk8s is running
high-availability: no
  datastore master nodes: 127.0.0.1:19001
  datastore standby nodes: none
addons:
  enabled:
    dashboard            # (core) The Kubernetes dashboard
    dns                  # (core) CoreDNS
    ha-cluster           # (core) Configure high availability on the current node
    helm                 # (core) Helm - the package manager for Kubernetes
    helm3                # (core) Helm 3 - the package manager for Kubernetes
    hostpath-storage     # (core) Storage class; allocates storage from host directory
    ingress              # (core) Ingress controller for external access
    metallb              # (core) Loadbalancer for your Kubernetes cluster
    metrics-server       # (core) K8s Metrics Server for API access to service metrics
    registry             # (core) Private image registry exposed on localhost:32000
    storage              # (core) Alias to hostpath-storage add-on, deprecated
  disabled:
    cert-manager         # (core) Cloud native certificate management
    community            # (core) The community addons repository
    host-access          # (core) Allow Pods connecting to Host services smoothly
    kube-ovn             # (core) An advanced network fabric for Kubernetes
    mayastor             # (core) OpenEBS MayaStor
    minio                # (core) MinIO object storage
    observability        # (core) A lightweight observability stack for logs, traces and metrics
    prometheus           # (core) Prometheus operator for monitoring and logging
    rbac                 # (core) Role-Based Access Control for authorisation

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