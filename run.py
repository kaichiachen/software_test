import tool.main as tool
import tool.const as const
import os

def produce_code(src_files, wr_files):
    mytool = tool.Changer()
    mytool.set_flag(const.flag_change_to_and_symbol | const.flag_change_to_or_symbol | const.flag_change_compare_symbol)
    for index in range(len(src_files)):
        print(src_files[index])
        mytool.handle_file(src_files[index], wr_files[index])

def find_all_file(src_path, wr_path):
    java_files = [os.path.join(root, name)
             for root, dirs, files in os.walk(src_path)
             for name in files
             if name.endswith((".java"))]
    wr_files = [wr_path + file.split(src_path)[1] for file in java_files]
    return java_files, wr_files

if __name__ == '__main__':
    project_src_path = 'javaproject/BMI/src'
    write_dir_path = 'javaproject/src1'
    src_files, wr_files = find_all_file(project_src_path, write_dir_path)
    produce_code(src_files,wr_files)