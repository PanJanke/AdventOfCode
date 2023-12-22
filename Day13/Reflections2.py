def read_input_from_file(file_path):
    try:
        with open(file_path, 'r') as file:
            groups = []
            current_group = []

            for line in file:
                line = line.strip()
                if not line:
                    if current_group:
                        groups.append(current_group)
                        current_group = []
                else:
                    current_group.append(line)

            if current_group:
                groups.append(current_group)

            return groups

    except FileNotFoundError:
        print(f"Error: File '{file_path}' not found.")
        return None
    except Exception as e:
        print(f"An error occurred: {e}")
        return None


def check_possible_mirror(lines, index):
    right = index + 1
    left = index
    count = 0
    while left >= 0 and right < len(lines):
        count += count_differences(lines[left], lines[right])
        right += 1
        left -= 1
    return count == 1


def count_differences(str1, str2):
    if len(str1) != len(str2):
        return 10

    differences = 0

    for char1, char2 in zip(str1, str2):
        if char1 != char2:
            differences += 1

    return differences


def find_mirror(lines):
    for i in range(len(lines) - 1):
        if count_differences(lines[i], lines[i + 1]) < 2 and check_possible_mirror(lines, i) is True:
            return i + 1
    return None


def get_output(rows, columns):
    if find_mirror(rows) is not None:
        return 100 * find_mirror(rows)
    else:
        return find_mirror(columns)


def task2():
    groups_of_lines = read_input_from_file("day13.txt")

    sum = 0

    for i in range(len(groups_of_lines)):
        rows = groups_of_lines[i]
        columns = list(zip(*rows))
        sum += get_output(rows, columns)

    print(sum)


task2()
