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


base = "https://api-lockitem-ccbscyfmgvc5a9eu.canadacentral-01.azurewebsites.net/api/v1/"
with open("source.json", encoding="utf-8") as fs:
    source = JSONObject(json.load(fs))


def post(iterable, route):
    for item in iterable:
        requests.post(route, json=item.__dict__)

post(source.users, base + "users")
post(source.categories, base + "product-categories")
post(source.products, base + "products")

stores = base + "stores"
post(source.stores, stores)

for product in source.storeProducts:
    productRoute = f"{stores}/{product.storeId}/products"
    requests.post(productRoute, json=product.__dict__)

