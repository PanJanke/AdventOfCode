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

    while left >= 0 and right < len(lines):
        if lines[left] != lines[right]:
            return False
        right += 1
        left -= 1

    return True


def find_mirror(lines):
    for i in range(len(lines) - 1):
        if lines[i] == lines[i + 1] and check_possible_mirror(lines, i) is True:
            return i + 1
    return None


def get_output(rows, columns):
    if find_mirror(rows) is not None:
        return 100 * find_mirror(rows)
    else:
        return find_mirror(columns)


def task1():
    groups_of_lines = read_input_from_file("day13.txt")

    sum = 0

    for i in range(len(groups_of_lines)):
        rows = groups_of_lines[i]
        columns = list(zip(*rows))
        sum += get_output(rows, columns)

    print(sum)


task1()
