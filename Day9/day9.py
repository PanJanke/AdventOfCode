

def calculate_differences(input_list):
    differences = []
    for i in range(1, len(input_list)):
        differences.append(input_list[i] - input_list[i - 1])
    return differences

def calculate_sum_task1(numbers):
    save_last_num = []
    result = 0
    while not all(num == 0 for num in numbers):
        save_last_num.append(numbers[-1])
        numbers = calculate_differences(numbers)

    for el in reversed(save_last_num):
        result += el

    return result


def calculate_sum_task2(numbers):
    save_first_num = []
    result = 0

    while not all(num == 0 for num in numbers):
        save_first_num.append(numbers[0])
        numbers = calculate_differences(numbers)

    for el in reversed(save_first_num):
        result = el - result

    return result


lines = open("test.txt").read().splitlines()

sum_task1 = 0
sum_task2 = 0

for line in lines:
    number = list(map(int, line.split(" ")))
    sum_task1 += calculate_sum_task1(number)
    sum_task2 += calculate_sum_task2(number)

print(sum_task1)
print(sum_task2)
