from xml.etree import ElementTree as et
import sys
import os

class XMLCombiner(object):
    def __init__(self, filenames):
        assert len(filenames) > 0, 'No filenames!'
        # save all the roots, in order, to be processed later
        self.roots = [et.parse(f).getroot() for f in filenames]

    def combine(self):
        for r in self.roots[1:]:
            # combine each element with the first one, and update that
            self.combine_element(self.roots[0], r)
        # return the string representation
        return et.tostring(self.roots[0])

    def combine_element(self, one, other):
        """
        This function recursively updates either the text or the children
        of an element if another element is found in `one`, or adds it
        from `other` if not found.
        """
        # Create a mapping from tag name to element, as that's what we are fltering with
        mapping = {el.tag: el for el in one}
        for el in other:
            if len(el) == 0:
                # Not nested
                try:
                    # Update the text
                    mapping[el.tag].text = el.text
                    print("TAG : ", el.text)
                except KeyError:
                    # An element with this name is not in the mapping
                    mapping[el.tag] = el

                    # Add it
                    one.append(el)
            else:
                try:
                    # Recursively process the element, and update it in the same way
                    self.combine_element(mapping[el.tag], el)
                except KeyError:
                    # Not in the mapping
                    mapping[el.tag] = el
                    # Just add it
                    one.append(el)


def combineUaxFiles(File1,File2,File3):
    r = XMLCombiner((File1,File2)).combine()
    tempFile = File3+'Temp'
    orig_stdout = sys.stdout
    sys.stdout = open(tempFile, 'w')
    # print('-' * 20)
    print(r)
    sys.stdout = orig_stdout
    with open(tempFile, 'r') as infile, open(
            File3, 'w') as outfile:
        temp = infile.read().replace("\\n", "").replace("b'", "").replace(">'", ">")
        outfile.write(temp)
    os.remove(tempFile)



if __name__ == '__main__':
    combineUaxFiles("D:\Office\Tools\Test2\DatabaseExtraction21.txtuaxdirectory",
                    "D:\Office\Tools\Test2\DatabaseExtraction3.uaxdirectory",
                    "D:\Office\Tools\Test2\DatabaseExtractionCombined.uaxdirectory")


