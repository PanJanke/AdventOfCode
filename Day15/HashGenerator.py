def get_hash(string):
    result = 0
    for char in string:
        result += ord(char)
        result *= 17
        result %= 256

    return result


with open("day15.txt", 'r') as file:
    content = file.read()

result_list = [part.strip().replace("\n", "") for part in content.split(',')]

sum = 0
for el in result_list:
    sum += get_hash(el)
print(sum)
