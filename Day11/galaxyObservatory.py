def read_input_from_file(file_path):
    try:
        with open(file_path, 'r') as file:
            lines = file.readlines()
            input_2d_array = [list(line.strip()) for line in lines]

            return input_2d_array

    except FileNotFoundError:
        print(f"Error: File '{file_path}' not found.")
        return None
    except Exception as e:
        print(f"An error occurred: {e}")
        return None


def galaxy_expand_naive(input_array):
    num_rows = len(input_array)
    num_columns = len(input_array[0])
    i = 0
    j = 0

    while i < num_rows:
        if '#' not in input_array[i]:
            input_array.insert(i, input_array[i][:])
            num_rows += 1
            i += 1
        i += 1

    while j < num_columns:
        column = [input_array[i][j] for i in range(num_rows)]
        if '#' not in column:
            for i in range(num_rows):
                input_array[i].insert(j, input_array[i][j])
            num_columns += 1
            j += 1
        j += 1

    return input_array


def find_empty_rows_and_columns(input_array):
    empty_rows = []
    empty_columns = []

    num_rows = len(input_array)
    num_columns = len(input_array[0])

    for i in range(num_rows):
        if '#' not in input_array[i]:
            empty_rows.append(i)

    for j in range(num_columns):
        column = [input_array[i][j] for i in range(num_rows)]
        if '#' not in column:
            empty_columns.append(j)

    return empty_rows, empty_columns


def find_hash_positions(input_array):
    hash_positions = []

    for i in range(len(input_array)):
        for j in range(len(input_array[i])):
            if input_array[i][j] == '#':
                hash_positions.append((i, j))

    return hash_positions


def create_position_pairs(hash_positions):
    position_pairs = []

    for i in range(len(hash_positions)):
        for j in range(i + 1, len(hash_positions)):
            position_pairs.append((hash_positions[i], hash_positions[j]))

    return position_pairs


def check_empty_between_positions(position1, position2, empty_rows, empty_columns):
    CONST_EXPAND = 1000000
    x1, y1 = position1
    x2, y2 = position2
    x_added_value = 0
    y_added_value = 0

    for col in range(min(x1, x2) + 1, max(x1, x2)):
        if col in empty_columns:
            x_added_value += CONST_EXPAND - 1

    for row in range(min(y1, y2) + 1, max(y1, y2)):
        if row in empty_rows:
            y_added_value += CONST_EXPAND - 1

    if x1 > x2:
        x1 += x_added_value
    else:
        x2 += x_added_value

    if y1 > y2:
        y1 += y_added_value
    else:
        y2 += y_added_value

    return (x1, y1), (x2, y2)


def swap_coordinates(point_pair):
    (x1, y1), (x2, y2) = point_pair
    return (y1, x1), (y2, x2)


def manhattan_distance(point1, point2):
    x1, y1 = point1
    x2, y2 = point2
    return abs(x1 - x2) + abs(y1 - y2)


def task1():
    input_2d_array = read_input_from_file("day11.txt")
    galaxy = galaxy_expand_naive(input_2d_array)
    pos_pairs = create_position_pairs(find_hash_positions(galaxy))

    sum = 0
    for pair in pos_pairs:
        sum += manhattan_distance(pair[0], pair[1])
    print(sum)


def task2():
    galaxy = read_input_from_file("day11.txt")
    empty_rows, empty_columns = find_empty_rows_and_columns(galaxy)
    pos_pairs = create_position_pairs(find_hash_positions(galaxy))

    adjusted_pos_pairs = []
    for pair in pos_pairs:
        swap_coordinates(pair)
        new_pair = check_empty_between_positions(pair[0], pair[1], empty_columns, empty_rows)
        adjusted_pos_pairs.append(new_pair)

    sum = 0
    for pair in adjusted_pos_pairs:
        sum += manhattan_distance(pair[0], pair[1])

    print(sum)


task1()
task2()
