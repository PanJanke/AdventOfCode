DIRECTIONS = {
    'north': (-1, 0),
    'south': (1, 0),
    'east': (0, 1),
    'west': (0, -1)
}


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


def count_visited(array):
    count = 0
    for row in array:
        for element in row:
            if element != '.':
                count += 1
    return count


def go_by_direction(start, direction):
    dy, dx = DIRECTIONS[direction]
    y, x = start
    return y + dy, x + dx


def move_iterative(start, direction):
    stack = [(start, direction)]

    while stack:
        current, current_direction = stack.pop()

        y, x = current
        if 0 <= x < num_columns and 0 <= y < num_rows:
            if save[y][x] == current_direction:
                continue

            save[y][x] = current_direction

            if input_array[y][x] == '.':
                new_position = go_by_direction(current, current_direction)
                stack.append((new_position, current_direction))

            elif input_array[y][x] == '-':
                if current_direction == 'south' or current_direction == 'north':
                    stack.append((go_by_direction(current, 'east'), 'east'))
                    stack.append((go_by_direction(current, 'west'), 'west'))
                else:
                    new_position = go_by_direction(current, current_direction)
                    stack.append((new_position, current_direction))

            elif input_array[y][x] == '|':
                if current_direction == 'east' or current_direction == 'west':
                    stack.append((go_by_direction(current, 'north'), 'north'))
                    stack.append((go_by_direction(current, 'south'), 'south'))
                else:
                    new_position = go_by_direction(current, current_direction)
                    stack.append((new_position, current_direction))

            elif input_array[y][x] == '/':
                if current_direction == 'north':
                    stack.append((go_by_direction(current, 'east'), 'east'))
                elif current_direction == 'south':
                    stack.append((go_by_direction(current, 'west'), 'west'))
                elif current_direction == 'east':
                    stack.append((go_by_direction(current, 'north'), 'north'))
                elif current_direction == 'west':
                    stack.append((go_by_direction(current, 'south'), 'south'))

            elif input_array[y][x] == '\\':
                if current_direction == 'north':
                    stack.append((go_by_direction(current, 'west'), 'west'))
                elif current_direction == 'south':
                    stack.append((go_by_direction(current, 'east'), 'east'))
                elif current_direction == 'east':
                    stack.append((go_by_direction(current, 'south'), 'south'))
                elif current_direction == 'west':
                    stack.append((go_by_direction(current, 'north'), 'north'))


def clear_save():
    for i in range(num_columns):
        for j in range(num_rows):
            save[j][i] = '.'


def task1():
    start = 0, 0
    move_iterative(start, 'east')
    print(count_visited(save))
    clear_save()


def task2():
    max = 0

    for i in range(num_columns):
        start = 0, i
        move_iterative(start, 'south')
        energized = count_visited(save)
        clear_save()
        if max < energized:
            max = energized

    for i in range(num_columns):
        start = num_rows - 1, i
        move_iterative(start, 'north')
        energized = count_visited(save)
        clear_save()
        if max < energized:
            max = energized

    for i in range(num_rows):
        start = i, 0
        move_iterative(start, 'east')
        energized = count_visited(save)
        clear_save()
        if max < energized:
            max = energized

    for i in range(num_rows):
        start = i, num_columns - 1
        move_iterative(start, 'west')
        energized = count_visited(save)
        clear_save()
        if max < energized:
            max = energized

    print(max)


input_array = read_input_from_file("Day16.txt")
num_rows = len(input_array)
num_columns = len(input_array[0])
save = [['.' for _ in range(num_columns)] for _ in range(num_rows)]

task1()
task2()
