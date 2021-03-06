SUMMARY = "Chromium Storage examples"
DESCRIPTION = "Some examples files for the Chromium Storage library."
HOMEPAGE = "http://www.iot.bzh"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d"

FILESEXTRAPATHS_prepend := ":${THISDIR}/../../shared:"

DEPENDS = "chromium-storage"

SRC_URI = " \
           file://LICENSE \
           file://CMakeLists.txt \
           file://chromium_storage-1_connections.cc \
          "

inherit cmake

CXXFLAGS_append = " -I${STAGING_INCDIR}/chromium"
LDFLAGS_append = " -Wl,-rpath-link,${STAGING_LIBDIR}/chromium -Wl,-rpath,${libdir}/chromium"
LDFLAGS_remove = " -Wl,--as-needed"
EXTRA_OECMAKE_append = " -DLINK_LIBRARIES='-L${STAGING_LIBDIR}/chromium -lstorage -lbase'"


do_configure_prepend() {
       mkdir -p ${S}
       cp ${WORKDIR}/LICENSE ${S}
       cp ${WORKDIR}/CMakeLists.txt ${S}
       cp ${WORKDIR}/*.cc ${S}
}

FILES_${PN}-dbg += "${bindir}/chromium/.debug/*"
