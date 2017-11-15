import tool.const as const
import os
import errno

class Changer():
    def __init__(self):
        self.change_compare_symbol = True
        self.change_to_and_symbol = True
        self.change_to_or_symbol = True

    def set_flag(self,flag):
        if not (flag & const.flag_change_compare_symbol):
            self.change_compare_symbol = False
    
        if not (flag & const.flag_change_to_and_symbol):
            self.change_to_and_symbol = False
        
        if not (flag & const.flag_change_to_or_symbol):
            self.change_to_or_symbol = False
    
    def handle_file(self, src_file, write_file):
        with open(src_file) as fr:
            if not os.path.exists(os.path.dirname(write_file)):
                try:
                    os.makedirs(os.path.dirname(write_file))
                except OSError as exc:
                    if exc.errno != errno.EEXIST:
                        raise
            with open(write_file, 'w+') as fw:
                for line in fr.readlines():
                    if self.change_to_and_symbol:
                        line = self.change_or_to_and(line)
                    if self.change_to_or_symbol:
                        line = self.change_and_to_or(line)
                    fw.write(line)

    def change_or_to_and(self,line):
        line = line.replace('or','and')
        line = line.replace('||','&&') 
        return line

    def change_and_to_or(self,line):
        line = line.replace('and','or')
        line = line.replace('&&','||') 
        return line