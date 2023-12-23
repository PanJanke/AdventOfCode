from Utils.ReadFile import basic_lines_reader

def gravitation(line):
    line_list = list(line)
    for i in range(len(line_list) - 1, -1, -1):
        if line_list[i] == 'O':
            j = i + 1
            while j < len(line_list) and line_list[j] == '.':
                j += 1

            if j > len(line_list) - 1:
                j = len(line_list) - 1
            else:
                j -= 1
            line_list[j], line_list[i] = line_list[i], line_list[j]
    result_str = ''.join(line_list)
    return result_str


def reverse_string(input_str):
    return input_str[::-1]


def counter(line):
    count = 0
    for it, char in enumerate(line):
        if char == 'O':
            count += it + 1
    return count


def task1():
    lines = basic_lines_reader("day14.txt")
    columns = list(zip(*lines))

    result = 0
    for col in columns:
        column = reverse_string(col)
        result += counter(gravitation(column))
    print(result)

task1()
