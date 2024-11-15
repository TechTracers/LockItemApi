import json
import requests


class KeyableObject(object):

    def __getattribute__(self, key):
        try:
            return super(KeyableObject, self).__getattribute__(key)
        except AttributeError:
            return None


class JSONObject(KeyableObject):
    def __init__(self, source):
        for key, value in source.items():
            if isinstance(value, dict):
                value = JSONObject(value)
            elif isinstance(value, (list, tuple, set)):
                value = [JSONObject(it) if isinstance(it, dict) else it for it in value]
            setattr(self, key, value)

    def __repr__(self):
        return f"{self.__dict__}"


def first(iterable, default=None, key=None):
    if key is None:
        def key(it):
            return it
    for item in iterable:
        if key(item):
            return item
    return default


base = "http://localhost:8080/api/v1/"
with open("source.json", encoding="utf-8") as fs:
    source = JSONObject(json.load(fs))


def post(body, route, token=None):
    headers = None
    if token:
        headers = {"Authorization": f"Bearer {token}"}
    result = requests.post(route, json=body.__dict__, headers=headers)
    return result.status_code, JSONObject(result.json())


def printPost(body, route, token=None):
    code, body = post(body, route, token)
    print(body)


def posts(iterable, route, token=None):
    for item in iterable:
        printPost(item, route, token)


def login(username, password):
    code, response = post(JSONObject({"username": username, "password": password}), base + "users/login")
    if code != 200:
        raise ValueError("Login failed.")
    return response.token


def isAdmin(user):
    return user.role == "ADMIN";


def getAdminSource():
    admin = first(source.users, key=isAdmin)
    if not admin:
        raise ValueError("Admin not found")
    return admin


print("Users: ")
posts(source.users, base + "users")

admin = getAdminSource()
print("\nAdmin: {}".format(admin))
token = login(admin.username, admin.password)

print("\nToken: {}".format(token))

print("\nProduct categories: ")
posts(source.categories, base + "product-categories", token)

print("\nProducts: ")
posts(source.products, base + "products", token)

print("\nStores: ")
stores = base + "stores"
posts(source.stores, stores, token)

print("\nStore Products: ")
for product in source.storeProducts:
    productRoute = f"{stores}/{product.storeId}/products"
    printPost(product, productRoute, token)
